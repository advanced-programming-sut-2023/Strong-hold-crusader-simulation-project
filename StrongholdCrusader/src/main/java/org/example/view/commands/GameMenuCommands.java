package org.example.view.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    MAP_X(".* -x (?<mapX>\\d+).*"),
    MAP_Y(".* -y (?<mapY>\\d+).*"),
    TYPE(".* -type (?<type>\\w+).*"),
    SHOW_MAP("show map (-x \\d+ -y \\d+|-y \\d+ -x \\d+)"),
    DROP_BUILDING("drop building( -x| -y| -type| \\d+| \\w+)+"),
    SELECT_BUILDING("select building (-x \\d+ -y \\d+|-y \\d+ -x \\d+)"),
    SELECT_UNIT("select unit (-x \\d+ -y \\d+|-y \\d+ -x \\d+)"),
    SET_TEXTURE("set texture.+"),
    CLEAR("clear (-x \\d+ -y \\d+|-y \\d+ -x \\d+)"),
    DROP_ROCK("drop rock( -x| -y| -direction| \\d+| \\w)+"),
    DROP_TREE("drop tree( -x| -y| -type| \\d+| \\w+)+"),
    MOVE_UNIT("move unit to (-x -?\\d+ -y -?\\d+|-y -?\\d+ -x -?\\d+)"),
    PATROL_UNIT("^(?=(.* +-x1 (?<x1>-?[\\d]+)){1,})"
                        + "(?=(.* +-x2 (?<x2>-?[\\d]+)){1,}) "
                        + "(?=(.* +-y1 (?<y1>-?[\\d]+)){1,})"
                        +"(?=(.* +-y2 (?<y2>-?[\\d]+)){1,})"
                        +"patrol unit.+$"),
    DIG_TUNNEL("dig tunnel (-x -?\\d+ -y -?\\d+|-y -?\\d+ -x -?\\d+)"),
    POUR_OIL("pour oil -d (?<dir>[swne])"),
    CEASEFIRE("ceasefire selected unit"),
    ATTACK_BYSHOOT("attack (-x -?\\d+ -y -?\\d+|-y -?\\d+ -x -?\\d+)"),
    ATTACK_ENEMY("attack -e (?<x>-?\\d+) (?<y>-?\\d+)"),
    SET_STATE("set state (-x -?\\d+ -y -?\\d+|-y -?\\d+ -x -?\\d+) (?<state>(standing|defensive|offensive))"),
    STOP_PATROL("stop patrol"),
    NEXT_TURN("next turn");
    private final String regex;

    GameMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, GameMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if(matcher.matches()) return matcher;
        else return null;
    }
    private String[] patrolUnitRegexes = {"^(?=(.* +-x1 (?<x1>-?[\\d]+)){1,})"
            , "(?=(.* +-x2 (?<x2>-?[\\d]+)){1,}) "
            , "(?=(.* +-y1 (?<y1>-?[\\d]+)){1,})"
            , "(?=(.* +-y2 (?<y2>-?[\\d]+)){1,})"
            , "patrol unit.+$"};

    public String[] getPatrolUnitRegexes() {
        return patrolUnitRegexes;
    }
}
