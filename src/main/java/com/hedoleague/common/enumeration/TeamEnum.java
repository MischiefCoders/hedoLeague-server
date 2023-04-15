package com.hedoleague.common.enumeration;

import lombok.Getter;

@Getter
public enum TeamEnum {
  ARSENAL("ARSENAL", 57),
  CHELSEA("CHELSEA", 61),
  LIVERPOOL("LIVERPOOL", 64),
  MAN_CITY("MAN_CITY", 65),
  MAN_UNITED("MAN_UNITED", 66),
  TOTTENHAM("TOTTENHAM", 73);

  TeamEnum(String name, int id) {
    this.name = name;
    this.id = id;
  }

  private final String name;
  private final int id;

}