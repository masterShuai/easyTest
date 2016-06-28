(function() {

    //删除所有空格函数
    String.prototype.trim=function() {

        return this.replace(/(^\s*)|(\s*$)/g,'');
    }

    var app = angular.module('exam', []);

    //控制器配置信息
    app.controller('ExamSController',['$scope','$http',ExamSController]);
    app.controller('ExamMController',['$scope','$http',ExamMController] );

   //单选、判断页面控制器
    function ExamSController($scope,$http) {
        $scope.urls={
            examAllPaper:"/"
            ,examSingleAnswer:"/sitExam/single"
            ,examJudge:"/sitExam/judge"
        };

        $scope.examButtonCaption ={
            prev:"上一题"
            ,next:"下一题"
            ,caption_last:"提交"
            ,caption_next:"下一题"
        };

        $scope.paperConfig ={
            pageSize:0
            ,itemIndex:0
            ,itemNo:1
        };

       /* $scope.searchPara ={
         salesmanId:"13402066813"
         };

         $scope.postPara ={
         salesmanId:"13402066813",
         periodicty:"",
         paper : {"score":"0","correctCount":"0","items":[{"id":0,"topic":"测试标题","anwser1":"1","anwser2":"2","anwser3":"3","anwser4":"4","itemCategory":"高级","itemType":"","anwser":"","anwserReference":"1"}]}
         };*/

        //data
        $scope.item = {
            "id":0,
            "topic":"测试标题",
            "anwser1":"11",
            "anwser2":"22",
            "anwser3":"33",
            "anwser4":"44",
            "itemCategory":"高级",
            "itemType":"",
            "anwser":"",
            "anwserReference":"1"
        };

        //data
        $scope.paper = {"score":"0","correctCount":"0","items":[{"id":0,"topic":"测试标题","anwser1":"1","anwser2":"2","anwser3":"3","anwser4":"4","itemCategory":"高级","itemType":"","anwser":"","anwserReference":"1"}]};

        $scope.anwser1Visible=false;
        $scope.anwser2Visible=false;
        $scope.anwser3Visible=false;
        $scope.anwser4Visible=false;

        $scope.userChoose = "";

        /**
         * 将后台的数据读取到前端
         */
        $scope.loadPaper = function() {
            //var result = JavaScriptInterface.getUserData();
            $scope.userChoose = $("#choose").val();
            console.dir($scope.userChoose);

            if($scope.userChoose=="single"){
                $http.get($scope.urls.examSingleAnswer)
                    .success( function(response) {
                        $scope.paper = response.content.paper;

                        if($scope.paper.items.length>0){
                            $scope.paperConfig.pageSize = $scope.paper.items.length;
                            $scope.paperConfig.itemIndex = 0;
                            $scope.setItem();
                            console.dir($scope.item);
                        }
                        else{
                            $scope.paperConfig.pageSize = 0;
                            $scope.paperConfig.itemIndex = 0;
                        }
                    });
            }
            else if($scope.userChoose=="judge"){
                $http.get($scope.urls.examJudge)
                    .success( function(response) {
                        $scope.paper = response.content.paper;

                        if($scope.paper.items.length>0){
                            $scope.paperConfig.pageSize = $scope.paper.items.length;
                            $scope.paperConfig.itemIndex = 0;
                            $scope.setItem();
                            console.dir($scope.item);
                        }
                        else{
                            $scope.paperConfig.pageSize = 0;
                            $scope.paperConfig.itemIndex = 0;
                        }
                    });
            }
            else if($scope.userChoose=="last"){
                $http.get($scope.urls.examSingleAnswer)
                    .success( function(response) {
                        $scope.paper = response.content.paper;

                        if($scope.paper.items.length>0){
                            $scope.paperConfig.pageSize = $scope.paper.items.length;
                            $scope.paperConfig.itemIndex = 88;
                            $scope.setItem();
                            console.dir($scope.item);
                        }
                        else{
                            $scope.paperConfig.pageSize = 0;
                            $scope.paperConfig.itemIndex = 0;
                        }
                    });
            }
        };

        /**
         * 下一题
         */
        $scope.nextItem = function() {
            if ($scope.item.anwser == null || $scope.item.anwser == '') {
                $('#anwser-alert').modal('open');//显示提示
                return;
            }

            if($scope.paperConfig.itemIndex == $scope.paperConfig.pageSize - 1) {
                $scope.finishAnswer();
                return;
            }

            if($scope.paperConfig.itemIndex < $scope.paperConfig.pageSize - 1){
                $scope.paperConfig.itemIndex++;
                $scope.paperConfig.itemNo = $scope.paperConfig.itemIndex+1;
                if($scope.paperConfig.itemIndex == $scope.paperConfig.pageSize - 1) {
                    $scope.examButtonCaption.next=$scope.examButtonCaption.caption_last;
                }
            }

            $scope.setItem();
        };

        /**
         * 上一题
         */
        $scope.prevItem = function(){

            if($scope.paperConfig.itemIndex == $scope.paperConfig.pageSize) {
                $scope.examButtonCaption.next=$scope.examButtonCaption.caption_next;
            }
            if($scope.paperConfig.itemIndex > 0){
                $scope.paperConfig.itemIndex--;
                $scope.paperConfig.itemNo = $scope.paperConfig.itemIndex+1;
            }
            $scope.setItem();
        };

        //根据答案判断界面显示
        $scope.isImgDivVisible = function(a){
            return !($scope.item.anwser==null || $scope.item.anwser=='') && ($scope.item.anwser==a || $scope.item.anwserReference.trim()==a);
        };
        //根据答案判断界面显示
        $scope.getImageRWUrl = function(a){
            if($scope.item.anwserReference.trim()==a)
                return "/images/exam/ui_exam_right.png";
            if($scope.item.anwser==a && $scope.item.anwserReference.trim()!= a)
                return "/images/exam/ui_exam_wrong.png";
            return "";
        };
        //根据已选答案,判断选项显示样式
        $scope.getItemDivClass = function(a){
            if(a==$scope.item.anwser){
                return "dv-item-selected";
            }
            return "dv-item";
        };
        //界面显示
        $scope.getItemAnwserDivClass = function(a){
            if(a==$scope.item.anwserReference.trim()){
                return "dv-item-pic";
            }
            return "dv-item-pic-wrong";
        };

        /**
         * 将paper按照当前题号提取到item中,并在页面显示
         */
        $scope.setItem=function(){
            var action = 0;
            console.dir(action);
            action++;
            $scope.item =  $scope.paper.items[$scope.paperConfig.itemIndex];
            console.dir(action);
            action++;
            if($scope.item.anwser==null){
                $scope.item.anwser ="";
            }
            console.dir(action);
            action++;

            $scope.item.anwserReference = $scope.item.anwserReference.trim();
            console.dir(action);
            action++;
            $scope.item.anwserReference = $scope.item.anwserReference.toUpperCase();
            console.dir(action);
            action++;
            console.log("item.anwser="+$scope.item.anwser);
            console.dir(action);
            action++;

            $scope.anwser1Visible=$scope.item.anwser1!=null && $scope.item.anwser1.length > 0;
            console.dir(action);
            action++;
            $scope.anwser2Visible=$scope.item.anwser2!=null && $scope.item.anwser2.length > 0;
            console.dir(action);
            action++;
            $scope.anwser3Visible=$scope.item.anwser3!=null && $scope.item.anwser3.length > 0;
            console.dir(action);
            action++;
            $scope.anwser4Visible=$scope.item.anwser4!=null && $scope.item.anwser4.length > 0;
            console.dir(action);
            action++;


        };

        /**
         * 当用户单击选项时,将选项记录为答案
         * @param anwser选项(非答案内容)
         */
        $scope.setAnwser=function(anwser){
            if ($scope.item.anwser != null && $scope.item.anwser != '') {
                return;
            }
            $scope.item.anwser = anwser;
            $scope.caculateScore();
        };

        /**
         * 计算分数,用户答题正确数(包括用户使用"上一题"功能答对的题目)/题目总数*100%
         */
        $scope.caculateScore = function(){
            var count = 0;
            for (var i=0;i<$scope.paper.items.length;i++){
                var item1 = $scope.paper.items[i];
                if (item1.anwser == null || item1.anwser == '') {
                    continue;
                }
                if(item1.anwser===item1.anwserReference.trim()){
                    count++;
                }
            }
            $scope.paper.correctCount = count;
            $scope.paper.score =Math.round(100*count/$scope.paper.items.length);
            console.log(count);
        };

        /*$scope.initPaper = function() {
            for (var i=0;i<$scope.paper.items.length;i++){
                var item1 = $scope.paper.items[i];
                item1.anwser="";
            }
            $scope.paper.score=0;
            $scope.paper.correctCount=0;
            $scope.paperConfig.pageSize = $scope.paper.items.length;
            $scope.paperConfig.itemIndex = 0;
            $scope.paperConfig.itemNo =1;
            $scope.examButtonCaption.next = $scope.examButtonCaption.caption_next;
            $scope.setItem();
        };*/

        /*$scope.postPaper = function(){
            if($scope.paper.correctCount<18){
                $('#post-alert').modal('open');
                //alert("必须正确完成至少18题，请重新答题！");
                $scope.initPaper();
                return;
            }
            $scope.postPara.salesmanId = $scope.searchPara.salesmanId;
           $scope.postPara.paper =$scope.paper;
            $http.post($scope.urls.examValidationPost,$scope.postPara)
                .success( function(response) {
                    $scope.paper = response.content;
                    console.dir($scope.paper);
                    //结果反馈
                });
        };*/

        $scope.finishAnswer= function(){
            if ($scope.paper.score < 60) {
                $('#post-alert').modal('open');//显示提示

            } else {
                $('#post-success-alert').modal('open');//显示提示

            }
        }
        //跳转到主页
        $scope.toHomePage= function(){
            window.location.replace("/");
        }
    }

    //多选题页面控制器
    function ExamMController($scope,$http) {
        $scope.urls={
            examAllPaper:"/sitExam/a"
            ,examMultiAnswer:"/sitExam/multiple"
        };

        $scope.examButtonCaption ={
            prev:"上一题"
            ,judge:"提交"
            ,next:"下一题"
            ,caption_last:"结束"
            ,caption_next:"下一题"
        };

        $scope.paperConfig ={
            pageSize:0
            ,itemIndex:0
            ,itemNo:1
        };

        //data
        $scope.item = {
            "id":0,
            "topic":"测试标题",
            "anwser1":"11",
            "anwser2":"22",
            "anwser3":"33",
            "anwser4":"44",
            "itemCategory":"高级",
            "itemType":"",
            "anwser":"",
            "anwserReference":"1"
        };

        //data
        $scope.paper = {"score":"0","correctCount":"0","items":[{"id":0,"topic":"测试标题","anwser1":"1","anwser2":"2","anwser3":"3","anwser4":"4","itemCategory":"高级","itemType":"","anwser":"","anwserReference":"1"}]};

        $scope.anwser1Visible=false;
        $scope.anwser2Visible=false;
        $scope.anwser3Visible=false;
        $scope.anwser4Visible=false;

        $scope.userChoose = "";

        /**
         * 将后台的数据读取到前端
         */
        $scope.loadPaper = function() {
            $http.get($scope.urls.examMultiAnswer)
                .success( function(response) {
                    $scope.paper = response.content.paper;

                    if($scope.paper.items.length>0){
                        $scope.paperConfig.pageSize = $scope.paper.items.length;
                        $scope.paperConfig.itemIndex = 0;
                        $scope.setItem();
                        console.dir($scope.item);
                    }
                    else{
                        $scope.paperConfig.pageSize = 0;
                        $scope.paperConfig.itemIndex = 0;
                    }
                });
            $('#init-alert').modal('open');//显示提示
        };

        /**
         * 提交,下一题
         */
        $scope.nextItem = function() {
            if($scope.examButtonCaption.judge=="提交"){
                if ($scope.userChoose == null || $scope.userChoose == '') {
                    $('#anwser-alert').modal('open');//显示提示
                    return;
                }
                else{
                    //将userChoose转为item.answer
                    var aIndex = $scope.userChoose.indexOf('A');
                    var bIndex = $scope.userChoose.indexOf('B');
                    var cIndex = $scope.userChoose.indexOf('C');
                    var dIndex = $scope.userChoose.indexOf('D');

                    if(aIndex>-1){$scope.item.anwser = "A";}
                    if(bIndex>-1){$scope.item.anwser += "B";}
                    if(cIndex>-1){$scope.item.anwser += "C";}
                    if(dIndex>-1){$scope.item.anwser += "D";}

                    $scope.caculateScore();
                    $scope.examButtonCaption.judge="下一题";
                }

                //若是最后一题,显示结果提示
                if($scope.paperConfig.itemIndex == $scope.paperConfig.pageSize - 1) {
                    $scope.finishAnswer();
                    return;
                }

            }
            else{
                if($scope.paperConfig.itemIndex < $scope.paperConfig.pageSize - 1){
                    $scope.paperConfig.itemIndex++;
                    $scope.paperConfig.itemNo = $scope.paperConfig.itemIndex+1;
                    /*if($scope.paperConfig.itemIndex == $scope.paperConfig.pageSize - 1) {
                        $scope.examButtonCaption.judge=$scope.examButtonCaption.caption_last;
                    }*/
                }
                $scope.setItem();
            }
        };

        /**
         * 上一题
         */
        $scope.prevItem = function(){
            if($scope.paperConfig.itemIndex > 0){
                $scope.paperConfig.itemIndex--;
                $scope.paperConfig.itemNo = $scope.paperConfig.itemIndex+1;
            }
            $scope.setItem();

           /* if($scope.paperConfig.itemIndex == $scope.paperConfig.pageSize - 1) {
                $scope.examButtonCaption.judge=$scope.examButtonCaption.caption_next;
            }*/
        };

        //根据答案判断界面显示
        $scope.isImgDivVisible = function(a){
            var isVisiblae = !($scope.item.anwser==null || $scope.item.anwser=='');

            if($scope.item.anwser.indexOf(a)>-1||$scope.item.anwserReference.indexOf(a)>-1){
                isVisiblae&&true;
            }
            else if($scope.userChoose.indexOf(a)>-1){
                isVisiblae&&true;
            }
            else isVisiblae=false;

            return isVisiblae;
        };
        //根据答案判断界面显示
        $scope.getImageRWUrl = function(a){
            if($scope.item.anwser==null || $scope.item.anwser==''){
                reuturn = "";
            }

            if($scope.item.anwserReference.indexOf(a)>-1)
                return "/images/exam/ui_exam_right.png";
            else if($scope.item.anwser.indexOf(a)>-1&&$scope.item.anwserReference.indexOf(a)<0)
                return "/images/exam/ui_exam_wrong.png";
        };

        //根据已选答案,判断选项显示样式
        $scope.getItemDivClass = function(a){
            if($scope.userChoose.indexOf(a)>-1||$scope.item.anwser.indexOf(a)>-1){
                return "dv-item-selected";
            }
            return "dv-item";
        };

        //界面显示
        $scope.getItemAnwserDivClass = function(a){
            if(a==$scope.item.anwserReference.trim()){
                return "dv-item-pic";
            }
            return "dv-item-pic-wrong";
        };

        /**
         * 将paper按照当前题号提取到item中,并在页面显示
         */
        $scope.setItem=function(){
            $scope.userChoose = "";
            $scope.item =  $scope.paper.items[$scope.paperConfig.itemIndex];
            if($scope.item.anwser==null){
                $scope.item.anwser ="";
            }

            $scope.item.anwserReference = $scope.item.anwserReference.trim();
            $scope.item.anwserReference = $scope.item.anwserReference.toUpperCase();
            console.log("item.anwser="+$scope.item.anwser);

            $scope.anwser1Visible=$scope.item.anwser1!=null && $scope.item.anwser1.length > 0;
            $scope.anwser2Visible=$scope.item.anwser2!=null && $scope.item.anwser2.length > 0;
            $scope.anwser3Visible=$scope.item.anwser3!=null && $scope.item.anwser3.length > 0;
            $scope.anwser4Visible=$scope.item.anwser4!=null && $scope.item.anwser4.length > 0;

            if ($scope.item.anwser != null && $scope.item.anwser != '') {
                $scope.examButtonCaption.judge="下一题";
            }
            else{
                $scope.examButtonCaption.judge="提交";
            }
        };

        /**
         * 当用户单击选项时,将选项记录为答案
         * @param anwser选项(非答案内容)
         */
        $scope.setChoose=function(answer){
            console.dir("选择:"+answer);

            if ($scope.item.anwser != null && $scope.item.anwser != '') {
                return;
            }
            //console.dir( "当前共选择:"+$scope.userChoose);
            var chooseIndex = $scope.userChoose.indexOf(answer);
            if(chooseIndex>-1){
                $scope.userChoose = $scope.userChoose.substring(0,chooseIndex)+$scope.userChoose.substring(chooseIndex+1);
            }
            else{
                $scope.userChoose += answer;
            }
            console.dir( "变更后共选择:"+$scope.userChoose);
        };

        /**
         * 判断对错,计算分数
         */
        $scope.caculateScore = function(){
            var count = 0;
            for (var i=0;i<$scope.paper.items.length;i++){
                var item1 = $scope.paper.items[i];

                //用户未选择答案
                if (item1.anwser == null || item1.anwser == '') {
                    continue;
                }

                var right =0;
                for(var j=0;j<item1.anwser.length;j++){
                    //判断参考答案中的第j+1个,是否包含在用户选择的答案中
                    var answerIndex = item1.anwserReference.indexOf(item1.anwser.charAt(j));
                    if(answerIndex<0){
                        right = 0;
                        break;
                    }
                    else if(answerIndex>-1) right++;
                }

                if(right == 0){
                    continue;
                }
                else if(right == item1.anwserReference.length){
                    count++;
                }
                else if(right < item1.anwserReference.length){
                    count+=0.5;
                }
            }
            $scope.paper.correctCount = count;
            $scope.paper.score =100*count/$scope.paper.items.length;
            console.log(count);
        };

        /*$scope.initPaper = function() {
         for (var i=0;i<$scope.paper.items.length;i++){
         var item1 = $scope.paper.items[i];
         item1.anwser="";
         }
         $scope.paper.score=0;
         $scope.paper.correctCount=0;
         $scope.paperConfig.pageSize = $scope.paper.items.length;
         $scope.paperConfig.itemIndex = 0;
         $scope.paperConfig.itemNo =1;
         $scope.examButtonCaption.next = $scope.examButtonCaption.caption_next;
         $scope.setItem();
         };*/

        /*$scope.postPaper = function(){
         if($scope.paper.correctCount<18){
         $('#post-alert').modal('open');
         //alert("必须正确完成至少18题，请重新答题！");
         $scope.initPaper();
         return;
         }
         $scope.postPara.salesmanId = $scope.searchPara.salesmanId;
         $scope.postPara.paper =$scope.paper;
         $http.post($scope.urls.examValidationPost,$scope.postPara)
         .success( function(response) {
         $scope.paper = response.content;
         console.dir($scope.paper);
         //结果反馈
         });
         };*/

        $scope.finishAnswer= function(){
            if($scope.paper.score<60){
                $('#post-alert').modal('open');//显示提示
                return;
            }
            else{
                $('#post-success-alert').modal('open');//显示提示
                return;
            }
        }

        //跳转到主页
        $scope.toHomePage= function(){
            window.location.replace("/");
        }
    }


})();


