package controller;

import entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.CategoryService;

import java.util.List;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/5/2
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询到所有的分类
     *
     * @return 分类集合
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    @ResponseBody
    public List<Category> getAllCategory() {
        List<Category> categoryList = categoryService.selectAllCategory();
        return categoryList;

    }

    /**
     * 删除分类
     *
     * @param id 分类ID
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public void deleteCategoryById(@RequestParam String id) {
        Category category = new Category();
        category.setId(Integer.parseInt(id));
        categoryService.deleteCategory(category);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void updateArticle(@RequestBody Category category) {
        try {
            categoryService.updateCategory(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
