package JobOonja.Functions;

import JobOonja.Entities.Project;

import java.util.Comparator;

public  class SortByCDate implements Comparator<Project>
{
    // Used for sorting in ascending order of
    // roll number
    public int compare(Project a, Project b)
    {
        return a.getCreationDate().intValue() - b.getCreationDate().intValue();
    }
}
