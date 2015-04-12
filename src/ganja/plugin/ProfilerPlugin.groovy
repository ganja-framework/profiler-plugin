package ganja.plugin

import ganja.common.di.ContainerInterface
import ganja.common.plugin.PluginInterface
import ganja.component.di.loader.FileLoaderInterface
import ganja.component.di.loader.YmlFileLoader
import ganja.component.di.parser.DefinitionParser
import org.yaml.snakeyaml.Yaml

class ProfilerPlugin implements PluginInterface {

    FileLoaderInterface fileLoader
    DefinitionParser parser

    void registerServices(ContainerInterface container) {

        fileLoader = new YmlFileLoader(
            yaml: new Yaml(),
            prefix: getClass().getSimpleName()
        )

        parser = new DefinitionParser()

        parser.parse(fileLoader.load('/config/services.yml') as Map, container)
    }
}
