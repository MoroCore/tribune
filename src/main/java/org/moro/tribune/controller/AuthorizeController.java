package org.moro.tribune.controller;

import org.moro.tribune.bean.AccessTokenDTO;
import org.moro.tribune.bean.GitHubUser;
import org.moro.tribune.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Locale;

/*
*
* Create by codedrinker on 2021.10.13
*
* */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.client.redirect.uri}")
    private String redirectUri;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name = "state") String state) throws IOException {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
//        accessTokenDTO.setState(state);
//        System.out.println(code+state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);
//        System.out.println(accessTokenDTO.toString());
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken);
//        System.out.println(accessToken);
//        String accessToken = "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";
//        GitHubUser user = githubProvider.getUser(accessToken);
//        System.out.println(user.toString());
        return "index";
    }
}
