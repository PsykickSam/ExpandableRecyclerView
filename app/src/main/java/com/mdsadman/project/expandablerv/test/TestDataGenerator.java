package com.mdsadman.project.expandablerv.test;

import java.util.ArrayList;
import java.util.List;

class TestDataGenerator {

    public static List<TestParentModel> getParentData() {
        List<TestParentModel> testModels = new ArrayList<>();
        testModels.add(new TestParentModel("Parent Item"));
        testModels.add(new TestParentModel("Parent Item"));
        testModels.add(new TestParentModel("Parent Item"));
        testModels.add(new TestParentModel("Parent Item"));
        testModels.add(new TestParentModel("Parent Item"));
        testModels.add(new TestParentModel("Parent Item"));
        testModels.add(new TestParentModel("Parent Item"));
        testModels.add(new TestParentModel("Parent Item"));
        testModels.add(new TestParentModel("Parent Item"));
        testModels.add(new TestParentModel("Parent Item"));
        testModels.add(new TestParentModel("Parent Item"));
        testModels.add(new TestParentModel("Parent Item"));

        return testModels;
    }

    public static List<TestChildModel> getChildData(int bound) {
        List<TestChildModel> testModels = new ArrayList<>();
        testModels.add(new TestChildModel("Child Item"));
        testModels.add(new TestChildModel("Child Item"));
        testModels.add(new TestChildModel("Child Item"));
        testModels.add(new TestChildModel("Child Item"));
        testModels.add(new TestChildModel("Child Item"));
        testModels.add(new TestChildModel("Child Item"));
        testModels.add(new TestChildModel("Child Item"));
        testModels.add(new TestChildModel("Child Item"));
        testModels.add(new TestChildModel("Child Item"));
        testModels.add(new TestChildModel("Child Item"));
        testModels.add(new TestChildModel("Child Item"));
        testModels.add(new TestChildModel("Child Item"));

        if (bound > testModels.size() || bound < 1) {
            return testModels;
        }

        return testModels.subList(0, bound);
    }
}
