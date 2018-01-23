package com.zwj.blog.auth.dao;

import com.zwj.blog.auth.JwtUser;
import com.zwj.blog.auth.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;

import static org.hibernate.jpa.internal.QueryImpl.LOG;

@Service
public class UserDaoImpl implements UserDetailsService, MessageSourceAware{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static final String QUERY_USERS_BY_USERNAME = "select id,user_name,password,is_enabled,password_update_time "
            + "from t_user " + "where user_name = ?";

    public static final String QUERY_ROLES_BY_USERID = "select role_id " + "from t_user_role_map "
            + "where user_id = ?";

    protected MessageSourceAccessor messageSourceAccessor;


    @Override
    public void setMessageSource(MessageSource messageSource) {
        Assert.notNull(messageSource,"message can't be null!");
        this.messageSourceAccessor = new MessageSourceAccessor(messageSource);
    }

    private String authoritiesByUserIdQuery;
    private String usersByUsernameQuery;
    private String rolePrefix = "";

    public UserDaoImpl() {
        this.authoritiesByUserIdQuery = QUERY_ROLES_BY_USERID;
        this.usersByUsernameQuery = QUERY_USERS_BY_USERNAME;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        List<User>  userList = this.loadUserListByUsername(name);

        User user = userList.get(0);

        Set<GrantedAuthority> authoritySet = new HashSet<>();

        authoritySet.addAll(loadUserAuthorities(user.getId()));

        List<GrantedAuthority> authorityList = new ArrayList<>(authoritySet);
        if(authorityList.size() == 0) {
            LOG.debug("User '" + name + "' has no authorities and will be treated as 'not found'");

            throw new UsernameNotFoundException(this.messageSourceAccessor.getMessage("JdbcDaoImpl.noAuthority",
                    new Object[] { name }, "User {0} has no GrantedAuthority"));
        }
        return createUserDetails(user, authorityList);
    }

    private UserDetails createUserDetails(User user, List<GrantedAuthority> authorityList) {
        return new JwtUser(user.getId(), user.getUsername(), user.getPassword(),
                authorityList, user.getEnabled(), user.getLastPasswordResetDate());

    }


    private List<GrantedAuthority> loadUserAuthorities(String id) {
        return jdbcTemplate.query(this.authoritiesByUserIdQuery, new String[] {id},
                (resultSet, i)  -> {
                    String roleId = rolePrefix + resultSet.getString(1);
                    return  new SimpleGrantedAuthority(roleId);
                }
        );
    }

    private List<User> loadUserListByUsername(String name) {
        return jdbcTemplate.query(this.usersByUsernameQuery, new String[] {name},
                (rs, i) -> {
                    String userid = rs.getString(1);
                    String username = rs.getString(2);
                    String password = rs.getString(3);
                    int enabled = rs.getInt(4);
                    Date lastPasswordResetDate = rs.getDate(5);
                    return new User(userid, username, password, enabled, lastPasswordResetDate);
                }
        );
    }
}
