/*
 * Copyright (C) 2014 UndeadScythes <udscythes@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.undeadscythes.jogger;

import java.io.IOException;

public class Jog {
    public static Output execute(String command) {
        Process process;
        try {
            process = Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            return new Output(ex.toString());
        }
        Output output = new Output(process);
        output.listen();
        return output;
    }

    private Jog() {}
}
