package controller;

import entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
}
