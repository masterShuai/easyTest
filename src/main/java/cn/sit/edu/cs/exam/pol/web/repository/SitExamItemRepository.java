package cn.sit.edu.cs.exam.pol.web.repository;

import cn.sit.edu.cs.exam.pol.web.domain.exam.SitExamItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/10/19.
 */
@Repository
public interface SitExamItemRepository extends CrudRepository<SitExamItem, Long> {
}
