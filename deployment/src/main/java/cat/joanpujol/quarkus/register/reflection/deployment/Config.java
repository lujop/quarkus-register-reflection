package cat.joanpujol.quarkus.register.reflection.deployment;

import java.util.List;

import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;

@ConfigRoot(phase = ConfigPhase.BUILD_TIME)
@ConfigMapping(prefix="quarkus.quarkus-register-reflection")
public interface Config {

    interface ExternalDependency {
        /**
         * External dependency group name
         */
        String group();
        /**
         * External dependency artifact name
         */
        String artifact();

    }

    /**
     * External dependencies
     * @return
     */
    List<ExternalDependency> externalDependencies();

    /**
     * Class patterns
     * @return
     */
    List<String> classPatterns();

}
