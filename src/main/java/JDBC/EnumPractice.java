package JDBC;

public class EnumPractice {


    public static void main(String[] args) {

        Season s1 = Season.SUMMER ;

        takeSeasonAction( s1 );

        takeSeasonAction( Season.WINTER );


    }


    static void takeSeasonAction(Season season){

        switch (season){

            case SPRING:
                System.out.println("CELEBRATE NAWRUZ");
                break;
            case SUMMER:
                System.out.println("GO TO THE BEACH");
                break;
            case FALL:
                System.out.println("CELEBRATE Thanksgiving, Apple Picking");
                break;
            case WINTER:
                System.out.println("Snowboarding , Chirstmas , New Year , Hibernate ");
                break;
                default:
                    System.out.println("NOT A VALID SEASON !!!");

        }






    }


}


