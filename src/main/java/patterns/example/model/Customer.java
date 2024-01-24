package patterns.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Rental> rentals;


//    public String statement() {
//        double totalAmount = 0;
//        int frequentRenterPoints = 0;
//        String result = "Rental Record for " + getName() + "\n";
//        for (Rental each : rentals) {
//            double thisAmount = 0;
//            //determine amounts for each line
//            switch (each.getMovie().getPriceCode()) {
//                case REGULAR -> {
//                    thisAmount += 2;
//                    if (each.getDaysRented() > 2)
//                        thisAmount += (each.getDaysRented() - 2) * 1.5;
//                }
//                case NEW_RELEASE -> thisAmount += each.getDaysRented() * 3;
//                case CHILDRENS -> {
//                    thisAmount += 1.5;
//                    if (each.getDaysRented() > 3)
//                        thisAmount += (each.getDaysRented() - 3) * 1.5;
//                }
//            }
//            // add frequent renter points
//            frequentRenterPoints++;
//            // add bonus for a two day new release rental
//            if ((each.getMovie().getPriceCode() == NEW_RELEASE) && each.getDaysRented() > 1)
//                frequentRenterPoints++;
//            //show figures for this rental
//            result += "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
//            totalAmount += thisAmount;
//        }
//        //add footer lines
//        result += "Amount owed is " + totalAmount + "\n";
//        result += "You earned " + frequentRenterPoints + " frequent renter points";
//        return result;
//    }


}