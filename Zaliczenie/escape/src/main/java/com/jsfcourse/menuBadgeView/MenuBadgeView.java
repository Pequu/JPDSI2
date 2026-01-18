package com.jsfcourse.menuBadgeView;


import org.primefaces.model.badge.BadgeModel;
import org.primefaces.model.badge.DefaultBadgeModel;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;


@Named
@RequestScoped
public class MenuBadgeView {

    private BadgeModel badgeModel;

    @PostConstruct
    public void init() {
        badgeModel = DefaultBadgeModel.builder()
                .severity("danger")
                .build();
    }

    public BadgeModel getBadgeModel() {
        return badgeModel;
    }
}