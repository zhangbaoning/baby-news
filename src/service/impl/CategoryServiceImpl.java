package service.impl;

import dao.CategoryDao;
import entity.Category;
import org.springframework.stereotype.Service;
import service.CategoryService;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/4/10
 */
@Service

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao;
    /**
     * 保存
     *
     * @param category
     */
    public void insertCategory(Category category) {
        categoryDao.insert(category);
    }

    /**
     * 删除
     *
     * @param category
     */
    public void deleteCategory(Category category) {
        categoryDao.delete(category);

    }

    /**
     * 更新
     *
     * @param category
     */
    public void updateCategory(Category category) {
        categoryDao.updateByPrimaryKey(category);

    }

    /**
     * 查询
     *
     * @param category
     * @return
     */
    public Category selectCategory(Category category) {
        categoryDao.select(category);
        return null;
    }
}
