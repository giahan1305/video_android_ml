package com.example.caffeai;
import java.util.HashMap;
import java.util.Map;
public class MyClass {
    public static String extractClass(float[] y) {
        Map<Integer, String> classes_dic = new HashMap<>();
        classes_dic.put(-1, "No record");
        classes_dic.put(0, "ApplyEyeMakeup");
        classes_dic.put(1, "ApplyLipstick");
        classes_dic.put(2, "Archery");
        classes_dic.put(3, "BabyCrawling");
        classes_dic.put(4, "BalanceBeam");
        classes_dic.put(5, "BandMarching");
        classes_dic.put(6, "BaseballPitch");
        classes_dic.put(7, "Basketball");
        classes_dic.put(8, "BasketballDunk");
        classes_dic.put(9, "BenchPress");
        classes_dic.put(10, "Biking");
        classes_dic.put(11, "Billiards");
        classes_dic.put(12, "BlowDryHair");
        classes_dic.put(13, "BlowingCandles");
        classes_dic.put(14, "BodyWeightSquats");
        classes_dic.put(15, "Bowling");
        classes_dic.put(16, "BoxingPunchingBag");
        classes_dic.put(17, "BoxingSpeedBag");
        classes_dic.put(18, "BreastStroke");
        classes_dic.put(19, "BrushingTeeth");
        classes_dic.put(20, "CleanAndJerk");
        classes_dic.put(21, "CliffDiving");
        classes_dic.put(22, "CricketBowling");
        classes_dic.put(23, "CricketShot");
        classes_dic.put(24, "CuttingInKitchen");
        classes_dic.put(25, "Diving");
        classes_dic.put(26, "Drumming");
        classes_dic.put(27, "Fencing");
        classes_dic.put(28, "FieldHockeyPenalty");
        classes_dic.put(29, "FloorGymnastics");
        classes_dic.put(30, "FrisbeeCatch");
        classes_dic.put(31, "FrontCrawl");
        classes_dic.put(32, "GolfSwing");
        classes_dic.put(33, "Haircut");
        classes_dic.put(34, "Hammering");
        classes_dic.put(35, "HammerThrow");
        classes_dic.put(36, "HandstandPushups");
        classes_dic.put(37, "HandstandWalking");
        classes_dic.put(38, "HeadMassage");
        classes_dic.put(39, "HighJump");
        classes_dic.put(40, "HorseRace");
        classes_dic.put(41, "HorseRiding");
        classes_dic.put(42, "HulaHoop");
        classes_dic.put(43, "IceDancing");
        classes_dic.put(44, "JavelinThrow");
        classes_dic.put(45, "JugglingBalls");
        classes_dic.put(46, "JumpingJack");
        classes_dic.put(47, "JumpRope");
        classes_dic.put(48, "Kayaking");
        classes_dic.put(49, "Knitting");
        classes_dic.put(50, "LongJump");
        classes_dic.put(51, "Lunges");
        classes_dic.put(52, "MilitaryParade");
        classes_dic.put(53, "Mixing");
        classes_dic.put(54, "MoppingFloor");
        classes_dic.put(55, "Nunchucks");
        classes_dic.put(56, "ParallelBars");
        classes_dic.put(57, "PizzaTossing");
        classes_dic.put(58, "PlayingCello");
        classes_dic.put(59, "PlayingDaf");
        classes_dic.put(60, "PlayingDhol");
        classes_dic.put(61, "PlayingFlute");
        classes_dic.put(62, "PlayingGuitar");
        classes_dic.put(63, "PlayingPiano");
        classes_dic.put(64, "PlayingSitar");
        classes_dic.put(65, "PlayingTabla");
        classes_dic.put(66, "PlayingViolin");
        classes_dic.put(67, "PoleVault");
        classes_dic.put(68, "PommelHorse");
        classes_dic.put(69, "PullUps");
        classes_dic.put(70, "Punch");
        classes_dic.put(71, "PushUps");
        classes_dic.put(72, "Rafting");
        classes_dic.put(73, "RockClimbingIndoor");
        classes_dic.put(74, "RopeClimbing");
        classes_dic.put(75, "Rowing");
        classes_dic.put(76, "SalsaSpin");
        classes_dic.put(77, "ShavingBeard");
        classes_dic.put(78, "Shotput");
        classes_dic.put(79, "SkateBoarding");
        classes_dic.put(80, "Skiing");
        classes_dic.put(81, "Skijet");
        classes_dic.put(82, "SkyDiving");
        classes_dic.put(83, "SoccerJuggling");
        classes_dic.put(84, "SoccerPenalty");
        classes_dic.put(85, "StillRings");
        classes_dic.put(86, "SumoWrestling");
        classes_dic.put(87, "Surfing");
        classes_dic.put(88, "Swing");
        classes_dic.put(89, "TableTennisShot");
        classes_dic.put(90, "TaiChi");
        classes_dic.put(91, "TennisSwing");
        classes_dic.put(92, "ThrowDiscus");
        classes_dic.put(93, "TrampolineJumping");
        classes_dic.put(94, "Typing");
        classes_dic.put(95, "UnevenBars");
        classes_dic.put(96, "VolleyballSpiking");
        classes_dic.put(97, "WalkingWithDog");
        classes_dic.put(98, "WallPushups");
        classes_dic.put(99, "WritingOnBoard");
        classes_dic.put(100, "YoYo");
        // Thêm tất cả các cặp key-value khác tương ứng với giá trị trong từ điển vào đây

        int max_i = 0;
        for (int i = 1; i < y.length; i++) {
            if (y[i] > y[max_i]) {
                max_i = i;
            }
        }
        if (y[max_i] <= (1.0 / classes_dic.size())) {
            max_i = -1;
        }
        return classes_dic.get(max_i);
    }
}
