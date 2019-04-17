/**
 * 
 */
package com.pitang.desafio.api.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Carlos H. de Oliveira - carlos.h.oliveira@cho.eti.br
 *
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Telefone implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 2840507360232023858L;

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  protected Long id;

  @NotNull(message = "Invalid Fields")
  @NotBlank(message = "Missing fields")
  @Size(max = 9, min = 8, message = "The phone number must have 8 or 9 digits")
  private String number;

  @NotNull(message = "Invalid Fields")
  @NotBlank(message = "Missing fields")
  @Size(max = 2, min = 2)
  private String area_code;

  @NotNull(message = "Invalid Fields")
  @NotBlank(message = "Missing fields")
  @Size(max = 3, min = 3)
  private String country_code;

}
