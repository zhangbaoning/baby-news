package service;

import entity.Category;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/4/10
 */
public interface CategoryService {
    /**
     * 保存
     *
     * @param category
     */
    void insertCategory(Category category);

    /**
     * 删除
     *
     * @param category
     */
    void deleteCategory(Category category);

    /**
     * 更新
     *
     * @param category
     */

    void updateCategory(Category category);

    /**
     * 查询
     *
     * @param category
     * @return
     */

    Category selectCategory(Category category);
}
