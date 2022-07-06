package cat.joanpujol.quarkus.register.reflection.deployment;

import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.IndexView;
import org.jboss.logging.Logger;

import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.CombinedIndexBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.IndexDependencyBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;

class QuarkusRegisterReflectionProcessor {

    private static final Logger log = Logger.getLogger(QuarkusRegisterReflectionProcessor.class);
    private static final String FEATURE = "quarkus-register-reflection";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    public void indexExternalDependencies(BuildProducer<IndexDependencyBuildItem> indexExternalDependencies, Config config) {
        config.externalDependencies().forEach(ed -> {
            indexExternalDependencies.produce(new IndexDependencyBuildItem(ed.group(),
                    ed.artifact()));
            log.infov("Index external dependency {0} {1}",ed.group(),ed.artifact());
        });
    }

    @BuildStep
    public void build(BuildProducer<ReflectiveClassBuildItem> reflectiveClass, CombinedIndexBuildItem indexBuildItem, Config config) {
        IndexView index = indexBuildItem.getIndex();
        for (ClassInfo clazz : index.getKnownClasses()) {
            for(String classPattern : config.classPatterns()) {
                if (clazz.name().toString().matches(classPattern)) {
                    reflectiveClass.produce(new ReflectiveClassBuildItem(true, true, clazz.name().toString()));
                    log.debugv("Registered for reflection class {0} ",clazz.name().toString());
                }
            }
        }
    }
}
