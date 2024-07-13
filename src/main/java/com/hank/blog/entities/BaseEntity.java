package com.hank.blog.entities;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public abstract class BaseEntity {
  @Column(name = "created_at")
  LocalDateTime createdAt;

  @Column(name = "updated_at")
  LocalDateTime updatedAt;
}
