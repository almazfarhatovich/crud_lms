package almaz.controllers;
import almaz.models.Group;
import almaz.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/new")
    public String newGroup(@ModelAttribute("groups") Group group) {
        return "group/new";
    }

    @PostMapping()
    public String createGroup(@ModelAttribute("groups") Group group,Long id) {
        groupService.saveGroup(group, id);
        return "redirect:/group";
    }
    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("groups", groupService.findAll());
        return "group/getAll";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("groups", groupService.findById(id));
        return "group/findById";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("groups", groupService.findById(id));
        return "group/edit";
    }

    @PatchMapping("{id}")
    public String updateGroup(@ModelAttribute("groups") Group group,
                               @PathVariable("id") Long id) {
        groupService.updateGroup(id, group);
        return "redirect:/group";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        groupService.deleteById(id);
        return "redirect:/group";
    }
}
