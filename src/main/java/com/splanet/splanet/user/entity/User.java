package com.splanet.splanet.user.entity;

import com.splanet.splanet.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User extends BaseEntity {

  @NotBlank
  @Size(max = 100)
  @Column(name = "nickname", nullable = false, length = 100, unique = true)
  private String nickname;

  @Size(max = 2083)
  @Column(name = "profile_image", length = 2083)
  private String profileImage;

  @Builder.Default
  @Column(name = "is_premium")
  private Boolean isPremium = false;

}
