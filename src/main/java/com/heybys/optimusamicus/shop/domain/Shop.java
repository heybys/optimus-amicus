package com.heybys.optimusamicus.shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Entity(name = "shop")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "shop_id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "open", nullable = false)
  private boolean open = false;

  @Builder
  public Shop(String name, boolean open) {
    this.name = name;
    this.open = open;
  }
}
