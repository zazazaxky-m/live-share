package com.smktelkommlg.prakerinlistview;

import android.widget.TextView;

public class Result {
        String name, role, status, manufacturer;
        int id;

        public Result(String name, int id, String role, String status, String manufacturer){
            this.name = name;
            this.id = id;
            this.role = role;
            this.status = status;
            this.manufacturer = manufacturer;
        }


        public String getName(){
            return name;
        }

        public int getId(){
            return id;
        }

        public String getRole(){
            return role;
        }

        public String getStatus(){
            return status;
        }

        public String getManufacturer() {return manufacturer;}
}

