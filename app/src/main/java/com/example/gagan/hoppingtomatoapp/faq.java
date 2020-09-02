package com.example.gagan.hoppingtomatoapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class faq extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView, recyclerView1, recyclerView2;
    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_faq);
        collapsingToolbarLayout.setTitleEnabled(false);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getTitle());

        }

        //Recycler View

        recyclerView = findViewById(R.id.faq_recycler_view);
        recyclerView1 = findViewById(R.id.faq_recycler_view1);
        recyclerView2= findViewById(R.id.faq_recycler_view2);

        ArrayList<faq.FAQ> data = new ArrayList<>();
        ArrayList<faq.FAQ> data_chef = new ArrayList<>();
        ArrayList<faq.FAQ> data_guest = new ArrayList<>();

        String[][] qaarray = {
                {"Q: What is Hopping Tomato?", "Hopping Tomato is a platform that allows you to hire a professional and non-professional chef. Hopping Tomato offers a new culinary alternative, allowing you to enjoy a personalised gastronomic experience by the hands of a private chef in the comfort of your home. Moreover, Hopping Tomato gives the chefs the possibility to make a profit by doing what they really like."},
                {"\nQ: How does Hopping Tomato work?", "Hopping Tomato allows guests to reserve the services of a private chef in London only as of now. All the options of menu and the cuisines are provided on the website. When the guest reserves the menu and the profile of the chef they like the most, Hopping Tomato does not charge anything till the services have been rendered to the guest(s). All exchange of information and conversation is done on the website so if there is any issue we can resolve it within 48 hours of a complaint. A week after the service, Hopping Tomato pays the corresponding amount to the chef."},
                {"\nQ: How does Hopping Tomato earn a profit?", "Hopping Tomato earns a 15% commission per reservation."},
                {"\nQ: How can I contact Hopping Tomato?", "You can contact us through email, at info@hoppingtomato.com."},
                {"\nQ: Is it secure?", "We take security of the chefs and guests very seriously. We make sure we have few documents provided by the guest as well as the chef before we can approve and active any of the members."}
        };

        String[][] qaarray_chef = {
                {"Q: Who can be a chef?", "This platform is open to anyone who enjoys cooking. Hopping Tomato categorises the chefs according to their experience and professional degrees provided . The objective of the website is to offer a wide variety of chef profiles so that the guest can find the right menu and profile for every occasion."},
                {"\nQ: What is the procedure?", "You will need to create a limited company, get yourself an insurance and provide DBS check. This is to make sure the guest feel secure and do not hesitate before letting a stranger in their house. As you are not an employee of the Hopping Tomato but an independent business. The insurance provides you and the guest the security in case of a mishap. These documents are verified on regular intervals."},
                {"\nQ: Do I need to pay to use Hopping Tomato?", "No. Hopping Tomato is a platform that allows you to sign up and offer your services as a private chef free of charge. Hopping Tomato only earns 15% of the total amount of each booking."},
                {"\nQ: What is the process I should follow to receive requests through Hopping Tomato?", "To receive service requests from guests it is essential to have a complete chef profile. Once validated by the Hopping Tomato team, your profile will be active, and you can select the cuisines you can cook. Once you have selected the cuisines then you can pick the different courses you can cook. The guest will be able to reserve your services and make the payment online once you have completed the task. After the service, Hopping Tomato will pay the chef's share by bank transfer in a week’s time depending on the cycle of the payment."},
                {"\nQ: How much time do I have to answer or reject a guest request?", "We recommend you answer as quickly as possible because the guest receives various proposals from chefs in a few hours. As soon as the guest finds the profile that they like, they can reserve the service, the platform prioritises the chefs with a faster response time."},
                {"\nQ: How much do I charge for my services?", "The price has been set by Hopping Tomato depending on your experience and your culinary degree(s). Hopping Tomato is a platform through which chefs can obtain new guests."},
                {"\nQ: What must be included in the price of the menu?", "The total price should include:\n" +
                        "• 3 Course meal for 4 people\n" +
                        "• Ingredients\n" +
                        "• Utensils\n" +
                        "• Travel to the destination\n" +
                        "• Service\n" +
                        "• Cleaning up the cooking area after finished\n" +
                        "• If the guest wants to add any additional guest that will be charged as £7.25 per extra guest. You may or may not provide the service of buying food products required for cooking during your visit. The guest will provide their choice of big store such as M&S, Lidl, etc. The chef can purchase the food products from one of those places as requested by the guest. Then the chef can upload the receipt onto the site against the order which will be added to the final bill."},
                {"\nQ: How can I establish communication with my guest?", "All communication prior to service will happen through the website. This way, the chef and the guest will have a record of the messages that can be looked back at, in case there are any incidents. Once the reservation is set, the chef will be provided with the address of the guest. However, we recommend that you keep using the website to message, so that there is a record of every conversation in case of any inconvenience or misunderstanding."},
                {"\nQ: Why can't I call or send an email before the reservation is made?", "We require all contact between chefs and guests to be through the website before the reservation is made so that we can maintain your privacy, offer guest services and adhere to the terms and conditions of Hopping Tomato. Once the guest has made a reservation, the contact information will be available to both the chef and the guest. To allow them to finalise the last details in a timely manner. We recommend that you keep using the website to message, so that there is a record of every conversation in case of any inconvenience or misunderstanding."},
                {"\nQ: How do guest ratings work?", "Guest ratings are a key part of Hopping Tomato. After every service the guest receives a feedback email for the service that the chef provided. The feedback includes questions regarding the quality of the food, explanation of the dishes, cleaning of the utensils and kitchen as well as the overall service. Chefs with good evaluations will invoke higher confidence from guests which will be essential to have a strong profile."},
                {"\nQ: What happens if I don't attend a reserved service?", "In Hopping Tomato we take very seriously the responsibility and respect between customers and chefs. Every incident would be analysed individually by our team, to decide if the chef must be removed from our platform. Hopping Tomato bases its values on the mutual confidence between customers and chefs, that's why we provide the tools of communication; in order to interact before the reservation. In addition, we also provide the emails and phone numbers after reservation to inform the parties of any problems that may arise prior to service."},
                {"\nQ: Do I have to pay tax on my earnings?", "Hopping Tomato recommends that you consult your advisor to know your tax obligations as you are a business owner and not an employee of Hopping Tomato."},
        };

        String[][] qaarray_guest = {
                {"Q: Who are the chefs?","The chefs you can hire through Hopping Tomato are cooks that work full-time or part-time as private chefs. They can either be people who have the skills or acquired the skills over a period of time or got those skills from a culinary school."},
                {"\nQ: What is the procedure?","A guest must provide us with a driver’s license and a photo graph. This document is checked on regular intervals. We take security of guests and chefs very seriously."},
                {"\nQ: Why should I book a private chef?","By hiring a private chef, you will enjoy an unforgettable culinary experience without leaving the comfort of your home. Whether it is for dinner between friends, a romantic night in, or a family reunion, this service will exceed expectations. Choose your menu and the chef will take care of everything else. The chef can also purchase the food products that will be utilised in cooking from the choice of your big store (M&S, Lidl, etc). Once purchased the receipt will be uploaded against your total bill. You will have to validate the receipt so that can be added to your final bill."},
                {"\nQ: What does the service include?","3 course meal for 4 from the menu of your choice\n" +
                        "• Ingredients\n" +
                        "• Utensils for cooking if required (pots and pans)\n" +
                        "• Service\n" +
                        "• Cleaning up of the kitchen area after cooking\n" +
                        "• Any additional guests will be charged £7.25 per person also £2.00 as a service charge."},
                {"\nQ: How can I hire a chef through Hopping Tomato?","Once you have signed up and provided the documents required and you have been verified by the admin. You can start searching for the cuisine of your choice and the chefs will pop-up on the search in the 1-3-mile radius or chefs who serve your area. Then, you can get in touch with the chefs through the website to request or suggest changes in their proposals. When you have chosen a menu you like, you can reserve it.\n" +
                        "Once you have confirmed the reservation, we will provide your address to the chef’s, so they can plan their trip to your location. We will not charge your card till the chef has finished the cooking."},
                {"\nQ: How do I choose a chef?","We have rated the chef according to their past experiences and their culinary degree(s). Once you have accepted the chef and the order has gone through. You will be able to see the picture of the chef to know whom to let in the house. Very important factors, but don't forget to also look at the reviews given to the chef by previous guests. Read all the evaluations and check the scores other guests have given the chef. Use this information to judge for yourself and choose a chef right for you. In Hopping Tomato, we believe that the opinion of the guests is the best way to measure the quality of the chef. Don't forget to tell us what you think and review your chef after the dinner."},
                {"\nQ: Can I talk to the chef prior to making a reservation?","Once you have placed the order then you can chat with the chef using the website chat."},
                {"\nQ: What happens if the chef cancels the service?","We do not charge you until the chef has cooked the food and reported to the administrator of completion of the task."},
                {"\nQ: How does the guest review section work?","Guest reviews are a key aspect for Hopping Tomato since they allow us to obtain objective information, from the guests, about the chef. At the same time, they help future guests to choose the chef most suitably equipped for their event. The day after the event, the guest will receive a feedback survey, to evaluate the chef. They will be able to express their opinion and experience during the service."}
        };

        for (int i = 0; i < qaarray.length; i++)
            data.add(new faq.FAQ(qaarray[i][0], qaarray[i][1]));

        faq.Adapter adapter = new faq.Adapter(this, data);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


        for (int i = 0; i < qaarray_chef.length; i++)
            data_chef.add(new faq.FAQ(qaarray_chef[i][0], qaarray_chef[i][1]));

        faq.Adapter adapter_chef = new faq.Adapter(this, data_chef);

        RecyclerView.LayoutManager manager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(manager1);
        recyclerView1.setAdapter(adapter_chef);


        for (int i = 0; i < qaarray_guest.length; i++)
            data_guest.add(new faq.FAQ(qaarray_guest[i][0], qaarray_guest[i][1]));

        faq.Adapter adapter_guest = new faq.Adapter(this, data_guest);

        RecyclerView.LayoutManager manager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(manager2);
        recyclerView2.setAdapter(adapter_guest);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (back_pressed + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
            } else {
                Toast.makeText(getBaseContext(), "Back once again to exit the app!",
                        Toast.LENGTH_SHORT).show();
                back_pressed = System.currentTimeMillis();
            }
        }
    }

    class Adapter extends RecyclerView.Adapter<faq.ViewHolder> {

        Adapter() {

        }

        Context mContext;
        ArrayList<faq.FAQ> data;

        Adapter(Context context, ArrayList<faq.FAQ> data) {
            mContext = context;
            this.data = data;

        }

        @NonNull
        @Override
        public faq.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.
                    faq_list, viewGroup, false);
            return new faq.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull faq.ViewHolder viewHolder, int position) {

            faq.FAQ currentFAQ = data.get(position);

            viewHolder.questionTextView.setText(currentFAQ.getQuestion());
            viewHolder.questionTextView.setTextColor(Color.RED);
            viewHolder.answerTextView.setText(currentFAQ.getAnswer());
            viewHolder.answerTextView.setTextColor(Color.rgb(255,140,0));

        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView questionTextView;
        TextView answerTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.faq_question_text_view);
            answerTextView = itemView.findViewById(R.id.faq_answer_text_view);
        }
    }

    class FAQ {
        String question;
        String answer;


        FAQ(String question, String answer) {

            this.question = question;
            this.answer = answer;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.faq, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent i = new Intent();

        if (id == R.id.nav_home) {
            i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_faq) {

        } else if (id == R.id.nav_about) {
            i = new Intent(this, about.class);
            startActivity(i);
        } else if (id == R.id.nav_contact) {
            i = new Intent(this, contact.class);
            startActivity(i);
        } else if (id == R.id.nav_social) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
