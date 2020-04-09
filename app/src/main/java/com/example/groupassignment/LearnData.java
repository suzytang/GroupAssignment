package com.example.groupassignment;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class LearnData {
    private int id;
    private String category;
    private int type;
    private String text;
    private String translation;

    public LearnData() {
    }

    public LearnData(int id, String category, int type, String text, String translation) {
        this.id = id;
        this.category = category;
        this.type = type;
        this.text = text;
        this.translation = translation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public static ArrayList<LearnData> getLearnData() {
        ArrayList<LearnData> learnData = new ArrayList<>();
        learnData.add(new LearnData(1,"General",0,"Hello",null));
        learnData.add(new LearnData(2,"General",0,"Goodbye",null));
        learnData.add(new LearnData(3,"General",1,"How are you? ",null));
        learnData.add(new LearnData(4,"General",0,"Good",null));
        learnData.add(new LearnData(5,"General",0,"Bad",null));
        learnData.add(new LearnData(6,"General",0,"Please",null));
        learnData.add(new LearnData(7,"General",0,"Thank you",null));
        learnData.add(new LearnData(8,"General",0,"You’re welcome",null));
        learnData.add(new LearnData(9,"General",0,"Excuse me",null));
        learnData.add(new LearnData(10,"General",0,"I’m sorry",null));
        learnData.add(new LearnData(11,"Conversation",0,"Yes",null));
        learnData.add(new LearnData(12,"Conversation",0,"No",null));
        learnData.add(new LearnData(13,"Conversation",1,"What’s your name?",null));
        learnData.add(new LearnData(14,"Conversation",1,"My name is… ",null));
        learnData.add(new LearnData(15,"Conversation",1,"Nice to meet you",null));
        learnData.add(new LearnData(16,"Conversation",1,"Where are you from?",null));
        learnData.add(new LearnData(17,"Conversation",1,"I’m from",null));
        learnData.add(new LearnData(18,"Conversation",0,"Australia",null));
        learnData.add(new LearnData(19,"Conversation",0,"New Zealand",null));
        learnData.add(new LearnData(20,"Conversation",0,"Overseas ",null));
        learnData.add(new LearnData(21,"Understanding",1,"Do you speak English?",null));
        learnData.add(new LearnData(22,"Understanding",1,"I don’t understand",null));
        learnData.add(new LearnData(23,"Understanding",1,"I speak a little…",null));
        learnData.add(new LearnData(24,"Understanding",1,"Could you translate?",null));
        learnData.add(new LearnData(25,"Understanding",1,"Could you please speak a little slower?",null));
        learnData.add(new LearnData(26,"Understanding",1,"Could you write that down?",null));
        learnData.add(new LearnData(27,"Understanding",1,"Could you repeat that?",null));
        learnData.add(new LearnData(28,"Understanding",1,"How do you say…?",null));
        learnData.add(new LearnData(29,"Understanding",1,"What does… mean?",null));
        learnData.add(new LearnData(30,"Understanding",1,"I appreciate this",null));
        learnData.add(new LearnData(31,"Purchase",0,"Cheap",null));
        learnData.add(new LearnData(32,"Purchase",0,"Expensive",null));
        learnData.add(new LearnData(33,"Purchase",0,"Cost",null));
        learnData.add(new LearnData(34,"Purchase",0,"Price",null));
        learnData.add(new LearnData(35,"Purchase",0,"Fee",null));
        learnData.add(new LearnData(36,"Purchase",1,"This is too…",null));
        learnData.add(new LearnData(37,"Purchase",1,"How much does this cost?",null));
        learnData.add(new LearnData(38,"Purchase",1,"Could I see this one?",null));
        learnData.add(new LearnData(39,"Purchase",1,"I’ll give you… for it",null));
        learnData.add(new LearnData(40,"Purchase",1,"Where can I exchange money?",null));
        learnData.add(new LearnData(41,"Transport",0,"Bus",null));
        learnData.add(new LearnData(42,"Transport",0,"Train",null));
        learnData.add(new LearnData(43,"Transport",0,"Plane",null));
        learnData.add(new LearnData(44,"Transport",0,"Taxi",null));
        learnData.add(new LearnData(45,"Transport",1,"What time does the…arrive?",null));
        learnData.add(new LearnData(46,"Transport",1,"What time does the…depart?",null));
        learnData.add(new LearnData(47,"Transport",1,"Is this seat taken?",null));
        learnData.add(new LearnData(48,"Transport",1,"When is the next…?",null));
        learnData.add(new LearnData(49,"Transport",1,"Could you call me a taxi?",null));
        learnData.add(new LearnData(50,"Transport",1,"I’d like to go to…",null));
        learnData.add(new LearnData(51,"Food & Drinks",0,"Restaurant",null));
        learnData.add(new LearnData(52,"Food & Drinks",0,"Eat ",null));
        learnData.add(new LearnData(53,"Food & Drinks",0,"Breakfast",null));
        learnData.add(new LearnData(54,"Food & Drinks",0,"Lunch",null));
        learnData.add(new LearnData(55,"Food & Drinks",0,"Dinner ",null));
        learnData.add(new LearnData(56,"Food & Drinks",0,"Drink",null));
        learnData.add(new LearnData(57,"Food & Drinks",1,"What would you recommend?",null));
        learnData.add(new LearnData(58,"Food & Drinks",1,"Could I see the menu?",null));
        learnData.add(new LearnData(59,"Food & Drinks",1,"Could I get the bill?",null));
        learnData.add(new LearnData(60,"Food & Drinks",1,"That was delicious",null));
        learnData.add(new LearnData(61,"Directions",0,"Left ",null));
        learnData.add(new LearnData(62,"Directions",0,"Right",null));
        learnData.add(new LearnData(63,"Directions",0,"Forward ",null));
        learnData.add(new LearnData(64,"Directions",0,"Backward",null));
        learnData.add(new LearnData(65,"Directions",1,"I am lost",null));
        learnData.add(new LearnData(66,"Directions",1,"How do I get to…?",null));
        learnData.add(new LearnData(67,"Directions",1,"How far is…?",null));
        learnData.add(new LearnData(68,"Directions",1,"Can you show me the way to…?",null));
        learnData.add(new LearnData(69,"Directions",1,"Do you have a map?",null));
        learnData.add(new LearnData(70,"Directions",1,"Where can I find tourist information?",null));
        learnData.add(new LearnData(71,"Accommodation",1,"I have a reservation.",null));
        learnData.add(new LearnData(72,"Accommodation",0,"Room",null));
        learnData.add(new LearnData(73,"Accommodation",0,"Reservation",null));
        learnData.add(new LearnData(74,"Accommodation",0,"Available",null));
        learnData.add(new LearnData(75,"Accommodation",0,"Free",null));
        learnData.add(new LearnData(76,"Accommodation",1,"Do you have any rooms available?",null));
        learnData.add(new LearnData(77,"Accommodation",1,"Could I see the room?",null));
        learnData.add(new LearnData(78,"Accommodation",1,"I’d like to stay for… nights.",null));
        learnData.add(new LearnData(79,"Accommodation",1,"Is breakfast included?",null));
        learnData.add(new LearnData(80,"Accommodation",1,"Could I get a different room?",null));
        learnData.add(new LearnData(81,"Emergency",0,"Doctor",null));
        learnData.add(new LearnData(82,"Emergency",0,"Hospital",null));
        learnData.add(new LearnData(83,"Emergency",0,"Police",null));
        learnData.add(new LearnData(84,"Emergency",0,"Ambulance",null));
        learnData.add(new LearnData(85,"Emergency",0,"Firefighter",null));
        learnData.add(new LearnData(86,"Emergency",1,"I need help",null));
        learnData.add(new LearnData(87,"Emergency",1,"I need a…",null));
        learnData.add(new LearnData(88,"Emergency",1,"Call the…",null));
        learnData.add(new LearnData(89,"Emergency",1,"Can I use your phone?",null));
        learnData.add(new LearnData(90,"Emergency",1,"Leave me alone",null));

//        for(int i = 0; i < learnData.size(); i++) {
//            TranslateRequest tR = new TranslateRequest();
//            String result = null;
//            try {
//                result = tR.execute(learnData.get(i).getText()).get();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            learnData.get(i).setTranslation(result);
//        }

        return learnData;
    }

}