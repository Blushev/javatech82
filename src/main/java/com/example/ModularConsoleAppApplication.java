package com.example;

import com.example.modules.FileModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example", "com.example.modules"})
public class ModularConsoleAppApplication implements CommandLineRunner {

    @Autowired
    private ModuleService moduleService;

    public static void main(String[] args) {
        SpringApplication.run(ModularConsoleAppApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (args.length == 0) {
            System.out.println("Please provide the path to the file.");
            return;
        }

        String filePath = args[0];
        List<FileModule> supportedModules = moduleService.getModulesForFile(filePath);

        if (supportedModules.isEmpty()) {
            System.out.println("No modules found for the specified file.");
            return;
        }

        System.out.println("Available modules for the file:");
        for (int i = 0; i < supportedModules.size(); i++) {
            System.out.println((i + 1) + ". " + supportedModules.get(i).getDescription());
        }

        System.out.println("Enter the number of the module to execute (0 to exit):");
        int selectedModuleIndex = new Scanner(System.in).nextInt();

        if (selectedModuleIndex == 0) {
            return;
        }

        if (selectedModuleIndex > 0 && selectedModuleIndex <= supportedModules.size()) {
            FileModule selectedModule = supportedModules.get(selectedModuleIndex - 1);
            selectedModule.process(filePath);
        } else {
            System.out.println("Invalid module number.");
        }
    }
}