package com.gl.repository;

import com.gl.entity.MetaTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaTagsRepository extends JpaRepository<MetaTags, Integer> {
}