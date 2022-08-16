package com.smktelkommlg.prakerinlistview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends ArrayAdapter<Result> {
        private List<Result> resultsList;
        private Context mCtx;

    public ListViewAdapter(List<Result> resultsList, Context mCtx){
            super(mCtx, R.layout.result_list, resultsList);
            this.resultsList = resultsList;
            this.mCtx = mCtx;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            //getting the layoutinflater
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            //creating a view with our xml layout
            View listViewItem = inflater.inflate(R.layout.result_list, null, true);
            //getting text views
            TextView deviceName = listViewItem.findViewById(R.id.device_name);
            TextView deviceId  = listViewItem.findViewById(R.id.device_id);
            TextView deviceRole = listViewItem.findViewById(R.id.device_role);
            TextView deviceStatus = listViewItem.findViewById(R.id.inv_device_status);
            TextView deviceManufacturer = listViewItem.findViewById(R.id.inv_device_manufacturer);
            TextView img_url = listViewItem.findViewById(R.id.inv_pic_link);
            ImageView statusImg = listViewItem.findViewById(R.id.device_status);
            ImageView devicePhoto = listViewItem.findViewById(R.id.devices_photo);
            ImageView noPhoto = listViewItem.findViewById(R.id.no_photo);



            //Getting the superHero for the specified position
            Result results = resultsList.get(position);
            //setting superHero values to textviews
            deviceName.setText(results.getName());
            deviceId.setText(String.valueOf(results.getId()));
            deviceRole.setText(results.getRole());
            deviceStatus.setText(results.getStatus());
            img_url.setText(results.getManufacturer());
            if(deviceStatus.getText().toString().equals("active")){
                statusImg.setImageResource(R.drawable.badge_active);
            }
            if(deviceStatus.getText().toString().equals("failed")){
                statusImg.setImageResource(R.drawable.badge_failed);
            }
            if(deviceStatus.getText().toString().equals("inventory")){
                statusImg.setImageResource(R.drawable.badge_inventory);
            }
            if(deviceStatus.getText().toString().equals("offline")){
                statusImg.setImageResource(R.drawable.badge_offline);
            }
            if(deviceStatus.getText().toString().equals("planned")){
                statusImg.setImageResource(R.drawable.badge_planned);
            }
            if(deviceStatus.getText().toString().equals("staged")){
                statusImg.setImageResource(R.drawable.badge_staged);
            }
            String urlurl = img_url.getText().toString().toLowerCase(Locale.ROOT);
            if(urlurl.equals("3com")){
                img_url.setText("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/3com_logo.svg/2560px-3com_logo.svg.png");
            }
            if(urlurl.equals("cisco")){
                img_url.setText("https://www.freepnglogos.com/uploads/cisco-png-logo/new-cisco-logo-png-1.png");
            }
            if(urlurl.equals("alcatel")){
                img_url.setText("https://1000logos.net/wp-content/uploads/2020/05/Alcatel-Logo-1536x960.png");
            }
            if(urlurl.equals("apc")){
                img_url.setText("https://seeklogo.com/images/A/apc-by-schneider-logo-8F55B2FD60-seeklogo.com.png");
            }
            if(urlurl.equals("aten")){
                img_url.setText("https://4.bp.blogspot.com/-ks2RxQ1420o/W-k-fOZ8dcI/AAAAAAAAP58/8xn3e8eehBofrJjYY8HVvwz12c8Ewj1gACLcBGAs/s320/Aten.png");
            }
            if(urlurl.equals("checkpoint")){
                img_url.setText("https://www.checkpoint.com/wp-content/uploads/check-point-logo-large.png.webp");
            }
            if(urlurl.equals("brocade")){
                img_url.setText("https://seeklogo.com/images/B/Brocade-logo-A410FD01EC-seeklogo.com.png");
            }
            if(urlurl.equals("d-link")){
                img_url.setText("https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/D-Link_wordmark.svg/1024px-D-Link_wordmark.svg.png?20180321035927");
            }
            if(urlurl.equals("dell")){
                img_url.setText("https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Dell_Logo.svg/600px-Dell_Logo.svg.png");
            }
            if(urlurl.equals("dtc")){
                img_url.setText("https://www.broadcastrental.com/wp-content/uploads/2016/01/DTC-logo.png");
            }
            if(urlurl.equals("eaton")){
                img_url.setText("https://logos-download.com/wp-content/uploads/2016/03/Eaton_Corporation_logo-700x228.png");
            }
            if(urlurl.equals("emerson")){
                img_url.setText("https://brandslogos.com/wp-content/uploads/images/emerson-electric-logo-1.png");
            }
            if(urlurl.equals("engenius")){
                img_url.setText("https://upload.wikimedia.org/wikipedia/commons/5/53/Engenius-gray.png?20181116190853");
            }
            if(urlurl.equals("extron")){
                img_url.setText("https://brandslogos.com/wp-content/uploads/images/extron-electronics-logo.png");
            }
            if(urlurl.equals("hp")){
                img_url.setText("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ad/HP_logo_2012.svg/200px-HP_logo_2012.svg.png");
            }
            if(urlurl.equals("huawei")){
                img_url.setText("https://www.freepnglogos.com/uploads/huawei-logo-png/huawei-logo-png-hd-0.png");
            }
            if(urlurl.equals("ibm")){
                img_url.setText("https://www.freepnglogos.com/uploads/ibm-logo-png/ibm-logo-ibm-logos-download-12.png");
            }
            if(urlurl.equals("intel")){
                img_url.setText("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0e/Intel_logo_%282020%2C_light_blue%29.svg/790px-Intel_logo_%282020%2C_light_blue%29.svg.png");
            }
            if(urlurl.equals("juniper")){
                img_url.setText("https://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Juniper_Networks_logo.svg/1024px-Juniper_Networks_logo.svg.png?20220202005234");
            }
            if(urlurl.equals("kvm")){
                img_url.setText("https://i.netly.win/2017/06/KVM-tucha.png");
            }
            if(urlurl.equals("lenovo")){
                img_url.setText("https://logodownload.org/wp-content/uploads/2014/09/lenovo-logo-0.png");
            }
            if(urlurl.equals("linksys")){
                img_url.setText("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/Linksys_Logo_neu.svg/1600px-Linksys_Logo_neu.svg.png?20140903222246");
            }
            if(urlurl.equals("mcb")){
                img_url.setText("https://seeklogo.com/images/M/MCB-logo-8790284FA2-seeklogo.com.png");
            }
            if(urlurl.equals("ruckus")){
                img_url.setText("https://upload.wikimedia.org/wikipedia/commons/7/7d/CS-Ruckus-logo.png?20210519141053");
            }
            if(urlurl.equals("mikrotik")){
                img_url.setText("https://smkperbankanriau.sch.id/wp-content/uploads/2018/11/Mikrotik-logo.png");
            }
            if(urlurl.equals("nitgen")){
                img_url.setText("https://www.impro.net/wp-content/uploads/2018/01/nitgen_logo.png");
            }
            if(urlurl.equals("nutanix")){
                img_url.setText("https://upload.wikimedia.org/wikipedia/commons/thumb/e/ea/Nutanix_Logo.svg/758px-Nutanix_Logo.svg.png?20170102030128");
            }
            if(urlurl.equals("power")){
                img_url.setText("https://upload.wikimedia.org/wikipedia/commons/thumb/9/97/Power_logo.svg/1024px-Power_logo.svg.png?20171011152854");
            }
            if(urlurl.equals("quintet")){
                img_url.setText("https://upload.wikimedia.org/wikipedia/commons/2/23/Quintet-Private-Bank.png?20200121140947");
            }
            if(urlurl.equals("riello")){
                img_url.setText("https://www.riello.com/slovenia/engines/riello/images/riello.png");
            }
            if(urlurl.equals("sangfor")){
                img_url.setText("https://seeklogo.com/images/S/sangfor-logo-154CA0F776-seeklogo.com.png");
            }
            if(urlurl.equals("sophos")){
                img_url.setText("https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Sophos_logo.svg/408px-Sophos_logo.svg.png?20160513084831");
            }
            if(urlurl.equals("tp-link")){
                img_url.setText("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/TPLINK_Logo_2.svg/1599px-TPLINK_Logo_2.svg.png?20161226101252");
            }
            if(urlurl.equals("tellabs")){
                img_url.setText("https://upload.wikimedia.org/wikipedia/en/thumb/b/bb/Tellabs_logo.svg/288px-Tellabs_logo.svg.png?20200615153015");
            }
            if(urlurl.equals("zte")){
                img_url.setText("https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/ZTE-logo.svg/800px-ZTE-logo.svg.png");
            }
            if(urlurl.equals("zavio")){
                img_url.setText("https://www.advantesco.com/assets/images/zavio1.png");
            }
            if(urlurl.equals("ubiquiti")){
                img_url.setText("https://upload.wikimedia.org/wikipedia/commons/7/71/Ubiquiti_Logo.png?20160216185436");
            }
            if(urlurl.equals("batery")){
                img_url.setText("https://cdn-icons-png.flaticon.com/512/37/37534.png?w=1380&t=st=1655971148~exp=1655971748~hmac=ef1e0abd4bf5ac2bf8d3617d162a3cb6600bd959416b0fc23425ab8ef6b73067");
            }
            if(urlurl.equals("masterview")){
                img_url.setText("https://www.masterviewwindows.com/wp-content/uploads/2020/03/masterview-rr.png");
            }
            if(urlurl.equals("primatech")){
                img_url.setText("https://quebecwoodexport.com/wp-content/uploads/logo_primatech_200.png");
            }
            if(urlurl.equals("sun")){
                img_url.setText("https://upload.wikimedia.org/wikipedia/commons/thumb/8/8b/Sun-Logo.svg/300px-Sun-Logo.svg.png");
            }

            Glide.with(devicePhoto).load(img_url.getText().toString()).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    noPhoto.setVisibility(View.INVISIBLE);
                    return false;
                }
            }).into(devicePhoto);//_1175_01-FAPET-Gd3lt1.jpg"

            //returning the listitem
            return listViewItem;

        }


    }
