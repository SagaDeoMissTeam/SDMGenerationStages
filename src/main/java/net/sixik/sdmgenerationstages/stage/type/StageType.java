package net.sixik.sdmgenerationstages.stage.type;

public abstract class StageType {

    public String stage;
    public StageType(String stage){
        this.stage = stage;
    }

    public String getStage() {
        return stage;
    }

    public abstract <T,U> boolean execute(T object, U object1);
    public abstract boolean add(StageType stageType);
}
