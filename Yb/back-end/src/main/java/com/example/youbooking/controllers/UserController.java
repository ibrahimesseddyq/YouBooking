package com.example.youbooking.controllers;

import com.example.youbooking.config.JwtUtils;
import com.example.youbooking.dto.LoginDto;
import com.example.youbooking.dto.UserDto;
import com.example.youbooking.entities.*;
import com.example.youbooking.repositories.RoleRepository;
import com.example.youbooking.services.IClientService;
import com.example.youbooking.services.IProprietaireService;
import com.example.youbooking.services.IUserService;
import com.example.youbooking.services.dto.ResponseDTO;
import com.example.youbooking.utiles.DtoToEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
public class UserController {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    IUserService userService;
    @Autowired
   IClientService clientService;
    @Autowired
    IProprietaireService proprietaireService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/all")
    public ResponseDTO allUsers(){
        return new ResponseDTO("sucesss","users users",userService.findAllUsers());
    }


    @GetMapping("/usersBan")
    public List<User> findHotelsByStatusDesactive(){
        return userService.findUserByStatus(Status.Desactive);
    }

    @GetMapping("/oneUser/{telephone}")
    public ResponseDTO findUser(@PathVariable String telephone){
        return userService.findUserByTelephone(telephone);
    }

    @PutMapping("/bannUser/{id}")
    public ResponseDTO bannUser(@PathVariable Long id){
        return userService.bannerUser(id);
    }

    @PostMapping("/add")
    public ResponseDTO register(@Valid @RequestBody UserDto userDto){
        User user = DtoToEntity.userDtoToUser(userDto);
        System.out.println(user);
        Optional<Role> role = roleRepository.findById(user.getRole().getId());

        if (role.get().getNom().equals("client")){
            Client client = DtoToEntity.clientDtoToUser(userDto);
             clientService.addClient(client);
             return new ResponseDTO("success","success",client);

        }else if(role.get().getNom().equals("proprietaire")){
            Proprietaire proprietaire = DtoToEntity.propritaireDtoToUser(userDto);
            proprietaire.setStatus(Status.Desactive);
             proprietaireService.addPropritaire(proprietaire);
             return new ResponseDTO("success","success",proprietaire);
        }
//        else if(role.get().getNom().equals("admin")){
//            Admin admin = DtoToEntity.ad(userDto);
//            proprietaire.setStatus(Status.Desactive);
//            proprietaireService.addPropritaire(proprietaire);
//            return new ResponseDTO("success","success",proprietaire);
//        }
        return userService.addUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> auth(@RequestBody LoginDto login){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(),login.getPassword()));
        UserDetails user = userService.findUserByEmail(login.getEmail());
        if (user != null){
            System.out.println("token" + jwtUtils.generateToken(user));
            return ResponseEntity.ok(new ResponseDTO("success","token",jwtUtils.generateToken(user)));
        }
        return ResponseEntity.status(400).body(new ResponseDTO("bad request","user not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteUser(@PathVariable Long id){
    return userService.delete(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateUser(@RequestBody UserDto userDto, @PathVariable Long id){
        userDto.setId(id);
        System.out.println(userDto.getId());
       User user = DtoToEntity.userDtoToUser(userDto);
        return userService.updateUser(user);
    }

    @GetMapping("/search")
    public ResponseDTO searchUsers(@RequestParam(value = "nom",required = false) String nom
                                   ,@RequestParam(value = "telephone",required = false) String telephone,
                                   @RequestParam(value = "email",required = false) String email){
        return userService.searchUser(nom,telephone,email);
    }


}
