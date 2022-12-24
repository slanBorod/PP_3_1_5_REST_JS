package ru.kata.spring.boot_security.demo.Util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;


@Component
public class StringToRoleConverter implements Converter<String, Role> {
    @Override
    public Role convert(String source) {
        String [] param = source.split(":");
        Integer id = Integer.parseInt(param [0]);
        String name = param[1];

        return new Role(id, name);
    }
}
