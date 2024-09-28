package com.api.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationDto {
  @NotNull
  @Size(min = 4 , message = "Miminimum character should be 4 digits")
   private String name;
  @Email
   private String email;
  @Size(min=10 , max=10 ,message="minimum digit shuld be 10")
   private String mobile;
}
