package sayner.sandbox.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sayner.sandbox.dto.SingleResponseObjectDto;
import sayner.sandbox.dto.StatusEnum;
import sayner.sandbox.dto.extd.SingleResponseObjectDtpExt;
import sayner.sandbox.models.User;
import sayner.sandbox.services.UserService;

import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {

    private final UserService userService;

    @GetMapping
    public SingleResponseObjectDto getUsersList() throws IOException{

        SingleResponseObjectDto singleResponseObjectDto=new SingleResponseObjectDtpExt<Object>(
                StatusEnum.AllDoneWell,
                "Список пользователелй из бд",
                true,
                userService.getAllUsers()
        );

        return singleResponseObjectDto;
    }
}
