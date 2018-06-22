package com.example.demo;

import com.example.demo.entities.TodoEntity;

public class RowBuilder {

    public static String generateRow(TodoEntity todo)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<tr>");
            sb.append("<td>");
                if (todo.getStatus()) sb.append("<strike>");
                    sb.append(todo.getText());
                if (todo.getStatus()) sb.append("</strike>");
            sb.append("</td>");
            sb.append("<td>");
                if (todo.getStatus()) sb.append("<strike>");
                    sb.append(todo.getDueDate());
                if (todo.getStatus()) sb.append("</strike>");
            sb.append("</td>");
            sb.append("<td>");
                sb.append(todo.getStatus());
            sb.append("</td>");
            sb.append("<td>");
              sb.append("<button class=\"button button1\">Check</button>\n" +
                      "            <button class=\"button button2\">Edit</button>\n" +
                      "            <button class=\"button button3\">Delete</button>");
            sb.append("</td>");
        sb.append("</tr>");
        return sb.toString();
    }
}
