package com.suleimanov.vehiclecontrol.security.models;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

import static jakarta.persistence.TemporalType.TIMESTAMP;

// Указывает, что данный объект не сущность
// Позволяет вынести общие поля в родительский класс, но при этом не создавать для него отдельную таблицу.
@MappedSuperclass
// Прослушиватель сущностей. Отслеживает изменения в полях данного класса
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T> {

  // Столбцы, помеченные @CreatedBy и @LastModifiedBy , заполняются именем участника, создавшего или последним изменившего сущность.
  // Информация поступает из экземпляра аутентификации SecurityContext
  @Column(nullable = false, updatable = false)
  @CreatedBy
  protected T createdBy;

  @Column(nullable = false, updatable = false)
  @CreatedDate
  @Temporal(TIMESTAMP)
  protected Date createdDate;

  @LastModifiedBy
  protected T lastModifiedBy;

  @LastModifiedDate
  @Temporal(TIMESTAMP)
  protected Date lastModifiedDate;

  public T getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(T createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public T getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(T lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public Date getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }
}
