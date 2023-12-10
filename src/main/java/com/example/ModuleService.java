package com.example;

import com.example.modules.FileModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleService {

    private final List<FileModule> modules = new ArrayList<>();

    @Autowired
    public ModuleService(List<FileModule> fileModules) {
        this.modules.addAll(fileModules);
    }

    public List<FileModule> getModules() {
        return modules;
    }

    public List<FileModule> getModulesForFile(String filePath) {
        List<FileModule> supportedModules = new ArrayList<>();
        for (FileModule module : modules) {
            if (module.supports(filePath)) {
                supportedModules.add(module);
            }
        }
        return supportedModules;
    }
}