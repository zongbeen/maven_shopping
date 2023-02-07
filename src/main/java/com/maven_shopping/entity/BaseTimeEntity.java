package com.maven_shopping.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class}) //Auditing적용
@MappedSuperclass //부모 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공
@Getter
@Setter
public class BaseTimeEntity {
    @CreatedDate //엔티티 생성 시간 자동 저장
    @Column(updatable = false)
    private LocalDateTime regTime;

    @LastModifiedDate //엔티티 값 변경 시간 자동 저장
    private LocalDateTime updateTime;

}
