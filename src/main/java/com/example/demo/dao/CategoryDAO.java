package com.example.demo.dao;

/**
 * @author LH
 * @description:
 * @date 2021-07-20 16:56
 */

import com.example.demo.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CategoryDAO extends JpaRepository<Category, Integer> {

}
