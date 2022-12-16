package com.techelevator.tenmo.model;

import org.springframework.context.annotation.Bean;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class UserDto {

   private int id;
   private String username;

   public UserDto() { }

   public UserDto(int id, String username) {

      this.id = id;
      this.username = username;
   }
   public String getUsername() {
      return username;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String findAllOtherUsers(Principal principal) {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   @Override
   public String toString() {
      return "UserDto{" +
              "id=" + id +
              ", username='" + username + '\'' +
              '}';
   }
}

