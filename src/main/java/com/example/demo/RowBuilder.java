package com.example.demo;

import com.example.demo.entities.TodoEntity;

public class RowBuilder {

    public static String generateRow(TodoEntity todo)
    {
        String dueString = todo.getDueDate().toString();
        dueString = dueString.substring(0, dueString.lastIndexOf(' '));

        StringBuilder sb = new StringBuilder();
        sb.append("<tr>");
            sb.append("<td>");
                if (todo.getStatus()) sb.append("<strike>");
                    sb.append(todo.getText());
                if (todo.getStatus()) sb.append("</strike>");
            sb.append("</td>");
            sb.append("<td>");
                if (todo.getStatus()) sb.append("<strike>");
                    sb.append(dueString);
                if (todo.getStatus()) sb.append("</strike>");
            sb.append("</td>");
            sb.append("<td>");
                sb.append(todo.getStatus());
            sb.append("</td>");
            sb.append("<td>");
              sb.append("<button class=\"button button1\" " + "onclick=\"toggleTodo(" + todo.getId() + ")\">"+(todo.getStatus() ? "Uncheck" : "&nbsp&nbspCheck&nbsp&nbsp") +"</button>\n" +
                      "            <button class=\"button button2\" onclick=\"editTodo(" + todo.getId() + ")\">Edit</button>\n" +
                      "            <button class=\"button button3\" onclick=\"deleteTodo(" + todo.getId() + ")\">Delete</button>");
            sb.append("</td>");
        sb.append("</tr>");
        return sb.toString();
    }
}
