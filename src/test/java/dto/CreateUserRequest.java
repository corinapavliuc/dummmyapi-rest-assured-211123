package dto;


import com.github.javafaker.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

    public class CreateUserRequest {

        private String id;
        private String firstName;
        private String lastName;
        private String email;
        private String registerDate;
        private String updateDate;
        private String picture;
        private String gender;
        private String title;
        private String age;

}
