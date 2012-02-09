package com.sinnerschrader.maven.readfiles;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

/**
 * @goal readfiles
 * @phase process-resources
 */
public class ReadFilesMojo extends AbstractMojo {

  /**
   * @parameter expression="${project}"
   * @required
   * @readonly
   */
  private MavenProject project;

  /**
   * @parameter
   */
  private File[] files;

  public void execute() throws MojoExecutionException {
    try {
      for (File file : files) {
        project.getProperties().setProperty(file.getName(), FileUtils.readFileToString(file));
      }
    } catch (IOException e) {
      throw new MojoExecutionException("Failed to read files", e);
    }
  }

}
