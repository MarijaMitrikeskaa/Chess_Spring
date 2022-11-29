//package com.fict.workinggroups.chess_puzzles.entity;
//
//import lombok.Data;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//import java.util.UUID;
//@Data
//@Entity
//@Table(name = "GuestUsers")
//public class Guest {
//    @Id @GeneratedValue(generator="system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
//    private String guestId;
////@Id
////@GeneratedValue(strategy = GenerationType.AUTO)
////private Long guestId;
//
//    private String name;
//
//    public Guest( String name) {
//
//        this.name = name;
//    }
//
//    public Guest(){
//
//    }
//
//    public String getId() {
//        return guestId;
//    }
//
//    public void setGuestId(String guestId) {
//        this.guestId = guestId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
