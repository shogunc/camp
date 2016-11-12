package com.karlsek.mercenarycamp.model.building;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "NOTICE_BOARD")
public class NoticeBoard extends Building {
}
