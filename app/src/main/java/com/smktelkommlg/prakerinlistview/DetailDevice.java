package com.smktelkommlg.prakerinlistview;

public class DetailDevice {
        String name, role, status, manufacturer, model, tenant, plat, serial, assetTag, site, location, rack, position, face, parentDevice,airflow, primaryIp, primary_ip4, primary_ip6 ;
        int id_detail;

        public DetailDevice(String name, int id, String role, String status, String manufacturer, String model, String tenant, String plat, String serial, String assetTag, String site, String location, String rack, String position, String face, String parentDevice, String airflow, String primaryIp, String primary_ip4, String primary_ip6){
            this.name = name;
            this.id_detail = id;
            this.role = role;
            this.status = status;
            this.manufacturer = manufacturer;
            this.model = model;
            this.tenant = tenant;
            this.plat = plat;
            this.serial = serial;
            this.assetTag = assetTag;
            this.site = site;
            this.location = location;
            this.rack = rack;
            this.position = position;
            this.face = face;
            this.parentDevice = parentDevice;
            this.airflow = airflow;
            this.primaryIp = primaryIp;
            this.primary_ip4 = primary_ip4;
            this.primary_ip6 = primary_ip6;
        }

        public String getNameDetail(){
            return name;
        }

        public int getId(){
            return id_detail;
        }

        public String getRole(){
            return role;
        }

        public String getStatus(){
            return status;
        }

        public String getManufacturer(){
            return manufacturer;
        }

        public String getModel(){
            return model;
        }

        public String getTenant(){
            return tenant;
        }

        public String getPlat(){
            return plat;
        }

        public String getSerial(){
            return serial;
        }

        public String getAssetTag(){
            return assetTag;
        }

        public String getSite(){
            return site;
        }

        public String getLocation(){
            return location;
        }

        public String getRack(){
            return rack;
        }

        public String getPosition() {return position;}

        public String getFace (){
            return face;
        }

        public String getParentDevice(){
            return parentDevice;
        }

        public String getAirflow(){
            return airflow;
        }

        public String getPrimaryIp(){
            return primaryIp;
        }

        public String getPrimary_ip4(){
            return primary_ip4;
        }

        public String getPrimary_ip6(){
            return primary_ip6;
        }
    }

