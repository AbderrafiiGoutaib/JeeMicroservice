package sid.authentidicationservice.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public class UserDetailServiceImplimentation implements UserDetailsService {
    private AccountService accountService;

    public UserDetailServiceImplimentation(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=accountService.loadUserByUsername(username);
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        appUser.getAppRoles().forEach(p->{
            authorities.add(new SimpleGrantedAuthority(p.getRoleName()));
        });
        return new User(appUser.getUsername(), appUser.getPassword(), authorities);

    }
}
