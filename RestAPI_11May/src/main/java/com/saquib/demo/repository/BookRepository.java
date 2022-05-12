package com.saquib.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.saquib.demo.model.Book;
//this is Dao Class
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}
