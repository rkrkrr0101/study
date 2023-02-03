package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/mapping/users")
public class MappingClassController {

    @GetMapping
    public String user() {
        return "get users";

    }

    @PostMapping
    public String addUser() {
        return "add users";

    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId) {
        return "find users" + userId;

    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "delete users" + userId;

    }

    @PatchMapping("/{userId}")
    public String patchUser(@PathVariable String userId) {
        return "patch users" + userId;

    }

}
