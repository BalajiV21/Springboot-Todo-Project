package SpringBoot_Project.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import SpringBoot_Project.web.model.Todo;

import SpringBoot_Project.web.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {

	@Autowired
	TodoService service;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping(value = "/listtodos", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("todos", service.retrieveTodos(name));
		return "listtodos";
	}

	private String getLoggedInUserName(ModelMap model) {
		return (String) model.get("name");
	}

	@RequestMapping(path = "/add-todos", method = RequestMethod.GET)
	
	public String showAddTodoPage( ModelMap model,Todo todo) {
		model.addAttribute("todo", new Todo(0, getLoggedInUserName(model), "",
				new Date(), false));
		return "todo";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		service.deleteTodo(id);
		return "redirect:/listtodos";
	}
	
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String updateTodo(@RequestParam int id,ModelMap model) {
		Todo todo = service.retrieveTodo(id);
		model.put("todo", todo);
		return "todo";
		
	}
	

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}
		
		todo.setUser(getLoggedInUserName(model));
		
		service.updateTodo(todo);

		return "redirect:/listtodos";
	}

	@RequestMapping(value = "/add-todos", method = RequestMethod.POST)

	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		if (result.hasErrors()){
				
			return "todo";
		}
		
		service.addTodo(getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(),
				false);
		return "redirect:/listtodos";
	}
}