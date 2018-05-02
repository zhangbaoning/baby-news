package service.impl;

import dao.CategoryDao;
import entity.Category;
import org.springframework.stereotype.Service;
import service.CategoryService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/4/10
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryDao categoryDao;

    /**
     * 保存
     *
     * @param category
     */
    @Override
    public void insertCategory(Category category) {
        categoryDao.insert(category);
    }

    /**
     * 删除
     *
     * @param category
     */
    @Override
    public void deleteCategory(Category category) {
        categoryDao.delete(category);

    }

    /**
     * 更新
     *
     * @param category
     */
    @Override
    public void updateCategory(Category category) {
        categoryDao.updateByPrimaryKeySelective(category);

    }

    /**
     * 查询
     *
     * @param category
     * @return
     */
    @Override
    public Category selectCategory(Category category) {
        categoryDao.select(category);
        return null;
    }

    /**
     * 查询所有的分类
     *
     * @return 分类集合
     */
    @Override
    public List<Category> selectAllCategory() {
        List<Category> categoryList = categoryDao.selectAll();
        return categoryList;
    }
}
