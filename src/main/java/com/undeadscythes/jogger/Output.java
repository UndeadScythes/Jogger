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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author UndeadScythes <udscythes@gmail.com>
 */
public class Output {
    private String stdErr = "";
    private String stdOut = "";
    private Process process;
    private BufferedReader errReader;
    private BufferedReader outReader;
    
    public Output(String message) {
        stdErr = message;
    }
    
    public Output(Process process) {
        this.process = process;
        errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        outReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    }
    
    public int listen() {
        String errLine;
        String outLine = null;
        try {
            while ((errLine = errReader.readLine()) != null || (outLine = outReader.readLine()) != null) {
                if (errLine != null) {
                    stdErr += errLine;
                }
                if (outLine != null) {
                    stdOut += outLine;
                }
            }
        } catch (IOException ex) {
            stdErr += ex.toString();
        }
        try {
            return process.waitFor();
        } catch (InterruptedException ex) {
            stdErr += ex.toString();
            return 1;
        }
    }
    
    public String getOutput() {
        return stdOut;
    }
    
    public String getError() {
        return stdErr;
    }
}
