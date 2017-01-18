package ru.at_consulting.dmp.km;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteException;
import org.apache.ignite.IgniteFileSystem;
import org.apache.ignite.compute.ComputeJob;
import org.apache.ignite.compute.ComputeJobResult;
import org.apache.ignite.compute.ComputeTaskSplitAdapter;
import org.apache.ignite.igfs.IgfsOutputStream;
import org.apache.ignite.igfs.IgfsPath;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.jetbrains.annotations.Nullable;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class CopyResourceToIgfsTask extends ComputeTaskSplitAdapter<ImmutableMap<String, String>, Boolean> {
    private final File input;

    @Override
    protected Collection<? extends ComputeJob> split(int gridSize, ImmutableMap<String, String> arg) throws IgniteException {
        log.debug("Try to load file {} to igfs", input.getName());

        return Collections.singleton(new CopyToIgfsJob(input, new IgfsPath("/tmp/" + input.getName())));
    }

    @Nullable
    @Override
    public Boolean reduce(List<ComputeJobResult> results) throws IgniteException {
        return results.stream().allMatch(ComputeJobResult::getData);
    }

    @RequiredArgsConstructor
    private static class CopyToIgfsJob implements ComputeJob {
        private static final String IGFS_NAME = "igfs";

        private final File input;
        private final IgfsPath output;

        private transient Ignite ignite;

        @Override
        public void cancel() {
        }

        @Override
        public Boolean execute() throws IgniteException {
            IgniteFileSystem fs = ignite.fileSystem(IGFS_NAME);
            try (
                    IgfsOutputStream os = fs.create(output, true);
                    FileInputStream fis = new FileInputStream(input)
            ) {
                os.transferFrom(new DataInputStream(fis), (int) input.length());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return true;
        }

        @IgniteInstanceResource
        public void setIgnite(Ignite ignite) {
            this.ignite = ignite;
        }
    }
}
