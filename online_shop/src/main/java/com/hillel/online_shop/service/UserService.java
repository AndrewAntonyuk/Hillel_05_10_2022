package com.hillel.online_shop.service;

import java.util.List;

public interface UserService<Request, Response> {

    Response findById(long id);

    Response findByLogin(String login);

    List<Response> findAll();

    Long create(Request request);

    void update(Request request);

    void delete(long id);

    void block(long id);

    void unblock(long id);

    void makeUserAdmin(long id);
}

